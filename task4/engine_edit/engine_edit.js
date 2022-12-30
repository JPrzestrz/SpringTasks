import {createLinkCell, getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayProducer();
    setTextNode('name',getParameterByName('producer'))
    setTextNode('producer',getParameterByName('producer'))
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplayProducer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/producers/' + getParameterByName('producer')+
    '/engines/' + getParameterByName('engine'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayProducer();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/producers/' + getParameterByName('producer')+
    '/engines/' + getParameterByName('engine'), true);

    const request = {
        'name': document.getElementById('name').value,
        'capacity':parseInt(document.getElementById('capacity').value),
        'yearz': parseInt(document.getElementById('year').value),
        'producer':getParameterByName('producer')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

