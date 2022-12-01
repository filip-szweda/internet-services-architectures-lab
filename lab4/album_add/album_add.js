import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

/**
 * Action event handled for updating album info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/albums/')

    const request = {
        'name': document.getElementById('name').value,
        'artist': parseInt(document.getElementById('artist').value),
        'release_date': document.getElementById('release_date').value,
        'score': getParameterByName("score")
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}