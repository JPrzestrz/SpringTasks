// clears all children of the provided element.
export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

// creates new table cell with button and it's action.
export function createButtonCell(text, action) {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode(text));
    button.classList.add('ui-control', 'ui-button');
    td.appendChild(button);
    button.addEventListener('click', action);
    return td;
}

// creates new table cell with hyperlink.
export function createLinkCell(text, url) {
    const td = document.createElement('td');
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    td.appendChild(a);
    return td;
}

// creates new table cell with a text.
export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}


/**
 * Returns value for query param.
 *
 * @param {string} name name of the query param
 * @returns {string} query param value
 */
export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}

// removes all children for selected element and adds new text node.
export function setTextNode(id, text) {
    let element = document.getElementById(id);
    clearElementChildren(element);
    element.appendChild(document.createTextNode(text));
}

