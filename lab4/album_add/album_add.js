import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    console.log('elo')
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

    const name = document.getElementById('name').value;
    const artist = document.getElementById('artist').value;
    const release_date = document.getElementById('release_date').value;
    const score = parseFloat(document.getElementById("score").value);

    console.log(name + ' ' + artist + ' ' + release_date + ' ' + score);

    const request = {
        'name': name,
        'artist': artist,
        'release_date': release_date,
        'score': score
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}