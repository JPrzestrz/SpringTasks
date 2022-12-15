import {getParameterByName} from '../../js/dom_utils.js';
import {getBackendUrl} from '../../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => postInfoAction(event));
});

function postInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {

        }

    };
    xhttp.open("POST", getBackendUrl() + '/api/producers/', true);
    const request = {
        'name': document.getElementById('name').value,
        'nip': parseInt(document.getElementById('nip').value),
        'yearz': parseInt(document.getElementById('yearz').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}