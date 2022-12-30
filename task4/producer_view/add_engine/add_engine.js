import {getParameterByName} from '../../js/dom_utils.js';
import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => postInfoAction(event));
    fetchAndDisplayProducer();
    
});

function postInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/producers/' + getParameterByName('producer') + '/engines/', true);
    const request = {
        'name': document.getElementById('name').value,
        'capacity': parseInt(document.getElementById('capacity').value),
        'yearz': parseInt(document.getElementById('yearz').value),
        'producer': getParameterByName('producer')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function fetchAndDisplayProducer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayProducer(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/producers/' + getParameterByName('producer'), true);
    xhttp.send();
}

function displayProducer(producer) {
    setTextNode('name', producer);
}
