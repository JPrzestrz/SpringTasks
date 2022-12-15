import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayProducers();
});

// fetches all producers.
function fetchAndDisplayProducers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        displayProducers(JSON.parse(this.responseText))
    };
    xhttp.open("GET", getBackendUrl() +'/api/producers', true);
    xhttp.send();
}

// updates the dom tree in order to display producers. 
function displayProducers(producers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    producers.producers.forEach(producer => {
        tableBody.appendChild(createTableRow(producer.name));
    })
}

// creates single row for entity.
function createTableRow(producer) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(producer));
    tr.appendChild(createLinkCell('click', '../producer_view/producer_view.html?producer=' + producer));
    tr.appendChild(createLinkCell('click', '../producer_edit/producer_edit.html?producer=' + producer));
    tr.appendChild(createButtonCell('delete', () => deleteProducer(producer)));
    return tr;
}

// deletes entity from backend and reloads table
function deleteProducer(producer) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayProducers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/producers/' + producer, true);
    xhttp.send();
}