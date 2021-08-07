//var stompClient = null;
//
//function setConnected(connected) {
//    console.log("setConnected");
//    $('#connect').prop("disabled", connected);
//    $("#disconnect").prop("disabled", !connected);
//    if (connected) {
//        $("#conversation").show();
//    }
//    else {
//        $("#conversation").hide();
//    }
//    $("#greetings").html("");
//}
//
//function connect() {
//    console.log("connect");
//    var socket = new SockJS('/websocket');
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
//        stompClient.subscribe('/topic/chat', function (chat) {
//        	showChat(JSON.parse(chat.body));
//        });
//    });
//}
//
//function disconnect() {
//    if (stompClient !== null) {
//        stompClient.disconnect();
//    }
//    setConnected(false);
//    console.log("Disconnected");
//}
//
//function sendName() {
//    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
//    sendChat();
//}
//
//function sendChat(name, message) {
//	stompClient.send("/app/chat", {}, JSON.stringify({'name': name, 'message': message}));
//}
//
//function showGreeting(message) {
//    $("#greetings").append("<tr><td>" + message + "</td></tr>");
//}
//function showChat(chat) {
//    $("#greetings").append("<tr><td>" + chat.name + " : " + chat.message + "</td></tr>");
//}
//
//$(function () {
//    $('form').on('submit', function (e) {
//        e.preventDefault();
//    });
//    console.log("hi");
////    connect();
////    sendName();
////    sendChat();
////    $("#connect").click(function() {
////        console.log("connect");
////        connect();
////    });
////    $("#disconnect").click(function() { disconnect(); });
////    $("#chatSend").click(function() { sendName(); });
////    $("#chatSend").click(function(){ sendChat(); });
//});