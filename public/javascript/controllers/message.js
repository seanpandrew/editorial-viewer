var messageEl = document.getElementById('messageBar');

var visibleClass = "message-bar--active";
var inVisibleClass = "message-bar";

function showMessage(text) {
    messageEl.innerHTML = text;
    messageEl.className = visibleClass;
    messageEl.addEventListener('click', hideMessage)
}

function hideMessage() {
    messageEl.innerHTML = '';
    messageEl.className = inVisibleClass;
    messageEl.removeEventListener('click', hideMessage)
}

module.exports = {
    showMessage: showMessage,
    hideMessage: hideMessage
};