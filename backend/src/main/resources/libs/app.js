var stompClient = null;

function setConnected(connected) {
    console.log("setConnected");
    document.getElementById("connect").prop("disabled", connected);
    document.getElementById("disconnect").prop("disabled", !connected);
    if (connected) {
        document.getElementById("conversation").show();
    }
    else {
        document.getElementById("conversation").hide();
    }
    document.getElementById("greetings").html("");
}

function connect() {
    console.log("connect");
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/chat', function (chat) {
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

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendChat() {
	stompClient.send("/app/chat", {}, JSON.stringify({'name': $("#name").val(), 'message': $("#chatMessage").val()}));
}

function showGreeting(message) {
    document.getElementById("greetings").append("<tr><td>" + message + "</td></tr>");
}
function showChat(chat) {
    document.getElementById("greetings").append("<tr><td>" + chat.name + " : " + chat.message + "</td></tr>");
}

$(function () {
    $('form').on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { sendName(); });
    $("#chatSend").click(function(){ sendChat(); });
});