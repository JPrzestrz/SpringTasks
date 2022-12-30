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
    fetchAndDisplayProducer();
    fetchAndDisplayEngines();
    passProducer();
});

function fetchAndDisplayEngines() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayEngines(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/producers/' + getParameterByName('producer') + '/engines', true);
    xhttp.send();
}

function displayEngines(engines) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    engines.engines.forEach(engine => {
        tableBody.appendChild(createTableRow(engine));
    })
}

function passProducer() {
    let tableBody = document.getElementById('tab');
    clearElementChildren(tableBody);
    let tr = document.createElement('tr');
    tr.appendChild(createLinkCell('Add engine', 'add_engine/add_engine.html?producer='
        + getParameterByName('producer')));
    tableBody.appendChild(tr);
}

function createTableRow(engine) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(engine.name));
    tr.appendChild(createLinkCell('view', '../engine_view/engine_view.html?producer='
        + getParameterByName('producer') + '&engine=' + engine.id));
    tr.appendChild(createLinkCell('edit', '../engine_edit/engine_edit.html?producer='
        + getParameterByName('producer') + '&engine=' + engine.id));
    tr.appendChild(createButtonCell('delete', () => deleteEngine(engine.id)));
    return tr;
}

function deleteEngine(engine) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayEngines();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/producers/' + getParameterByName('producer')
        + '/engines/' + engine, true);
    xhttp.send();
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
    setTextNode('name', producer.name);
    setTextNode('nip', producer.nip);
    setTextNode('yearz', producer.year);
}
