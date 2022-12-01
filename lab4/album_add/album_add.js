import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/albums/')

    const name = document.getElementById('name').value;
    const artist = document.getElementById('artist').value;
    const releaseDate = document.getElementById('releaseDate').value;
    const score = parseFloat(document.getElementById("score").value);

    const request = {
        'name': name,
        'artist': artist,
        'releaseDate': releaseDate,
        'score': score
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}