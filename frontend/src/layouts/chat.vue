<template>
    <div id="main-content" class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="form-inline" accept-charset="UTF-8">
                    <div class="form-group">
                        <label for="connect">WebSocket connection:</label>
                        <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    </div>
                </form>
                <form class="form-inline" onsubmit="disconnect(); return false;" accept-charset="UTF-8">
                    <div class="form-group">
                        <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-md-6">
                <form class="form-inline" onsubmit="sendChat(); return false;" accept-charset="UTF-8">
                    <div class="form-group">
                        <label for="name">What is your name?</label>
                        <input type="text" id="name" class="form-control" placeholder="Your name here..." />
                    </div>
                    <div class="form-group">
                        <label for="message">Input Message</label>
                        <input type="text" id="chatMessage" class="form-control" placeholder="message.." />
                    </div>
                    <button id="chatSend" class="btn btn-default" type="submit">Chat Send</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Messages</th>
                    </tr>
                    </thead>
                    <tbody id="greetings">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
export default { setup () {} }
</script>
