/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

console.log(location.host)
var ws = new WebSocket('wss://' + location.host + '/groupcall');
var participants = {};
var name;

window.onbeforeunload = function() {
	ws.close();
};

ws.onmessage = function(message) {
	let parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'existingParticipants':
	    console.log('existingParticipants');
		onExistingParticipants(parsedMessage);
		break;
	case 'newParticipantArrived':
	    console.log('newParticipantArrived');
		onNewParticipant(parsedMessage);
		break;
	case 'participantLeft':
	    console.log('participantLeft');
		onParticipantLeft(parsedMessage);
		break;
	case 'receiveVideoAnswer':
	    console.log('receiveVideoAnswer');
		receiveVideoResponse(parsedMessage);
		break;
	case 'iceCandidate':
	    console.log('iceCandidate');
		participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
	        if (error) {
		      console.error("Error adding candidate: " + error);
		      return;
	        }
	    });
	    break;
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

function register() {
	name = document.getElementById('rtcName').value;
	let room = document.getElementById('roomName').value;

	document.getElementById('room-header').innerText = 'ROOM ' + room;
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';

	let message = {
		id : 'joinRoom',
		name : name,
		room : room,
	}
	connect(room);
	sendMessage(message);
}

function joinConference(n, r) {
	name = n;
//	var room = document.getElementById('joinConferenceRoom').value;
//    console.log($(this));
//
	document.getElementById('room-header').innerText = 'ROOM ' + room;
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';

	let m = {
		id : 'joinRoom',
		name : n,
		room : r,
	}
	connect(r);
	sendMessage(m);
}

function onNewParticipant(request) {
	receiveVideo(request.name);
}

function receiveVideoResponse(result) {
	participants[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
		if (error) return console.error (error);
	});
}

function callResponse(message) {
	if (message.response != 'accepted') {
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
			if (error) return console.error (error);
		});
	}
}

function onExistingParticipants(msg) {
	let constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 320,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};
	console.log(name + " registered in room " + room);
	let participant = new Participant(name);
	participants[name] = participant;
	let video = participant.getVideoElement();

	let options = {
	      localVideo: video,
	      mediaConstraints: constraints,
	      onicecandidate: participant.onIceCandidate.bind(participant)
	    }
	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
		function (error) {
		  if(error) {
			  return console.error(error);
		  }
		  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});

	msg.data.forEach(receiveVideo);
}

function leaveRoom() {
	sendMessage({
		id : 'leaveRoom'
	});

	for ( let key in participants) {
		participants[key].dispose();
	}

	document.getElementById('join').style.display = 'block';
	document.getElementById('room').style.display = 'none';

	ws.close();
	window.location.reload();
}

function receiveVideo(sender) {
	let participant = new Participant(sender);
	participants[sender] = participant;
	let video = participant.getVideoElement();

	let options = {
      remoteVideo: video,
      onicecandidate: participant.onIceCandidate.bind(participant)
    }

	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
			function (error) {
			  if(error) {
				  return console.error(error);
			  }
			  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});
}

function onParticipantLeft(request) {
	console.log('Participant ' + request.name + ' left');
	var participant = participants[request.name];
	participant.dispose();
	delete participants[request.name];
}

function sendMessage(message) {
	let jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}

var stompClient = null;

var choosedRoomName = null;

function setConnected(connected) {
    console.log("setConnected");
    $('#connect').prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect(room) {
    console.log("connect");
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/' + room, function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
        stompClient.subscribe('/topic/' + room, function (chat) {
        	showChat(JSON.parse(chat.body));
        });
    });

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//function sendName() {
//    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
//    sendChat();
//}

function sendChat(n, m, r) {
	stompClient.send("/app/" + r, {}, JSON.stringify({'name': n, 'message': m}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showChat(chat) {
    $("#greetings").append("<tr><td class='msg'>" + chat.name + " : " + chat.message + "</td></tr>");
    $('#chatMessage').val('');
}

function rtcMute(name){
    let bt1 = document.getElementById('bt1')
    if(participants[name].rtcPeer.audioEnabled === true){
        participants[name].rtcPeer.audioEnabled = false;
        bt1.style.backgroundColor = "#8a8d8b";
        bt1.style.border = "0.5px solid #8a8d8b";
    } else{
        participants[name].rtcPeer.audioEnabled = true;
        bt1.style.backgroundColor = "#249752";
        bt1.style.border = "0.5px solid #249752";
    }
}

function rtcVideoVision(name){
    let bt2 = document.getElementById('bt2')
    if(participants[name].rtcPeer.videoEnabled === true){
        participants[name].rtcPeer.videoEnabled = false;
        bt2.style.backgroundColor = "#8a8d8b";
        bt2.style.border = "0.5px solid #8a8d8b";
    } else{
        participants[name].rtcPeer.videoEnabled = true;
        bt2.style.backgroundColor = "#249752";
        bt2.style.border = "0.5px solid #249752";
    }
}
//$(function () {
//    $('form').on('submit', function (e) {
//        e.preventDefault();
//    });
//    console.log("hi");
//    connect();
//    sendName();
//    sendChat();
//    $("#connect").click(function() {
//        console.log("connect");
//        connect();
//    });
//    $("#disconnect").click(function() { disconnect(); });
//    $("#chatSend").click(function() { sendName(); });
//    $("#chatSend").click(function(){ sendChat(); });
//});