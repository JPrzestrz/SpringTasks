import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayEngine();
});

function fetchAndDisplayEngine() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayEngine(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/producers/' + getParameterByName('producer') +
    '/engines/' + getParameterByName('engine'), true);
    xhttp.send();
}

function displayEngine(engine) {
    setTextNode('name', engine.name);
    setTextNode('capacity', engine.capacity);
    setTextNode('yearz', engine.year);
    setTextNode('producer', engine.producer);
}
