import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayAlbum();
});

function fetchAndDisplayAlbum() {
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

    xhttp.open("GET", getBackendUrl() + '/api/albums/' + getParameterByName('album'))
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayAlbum();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/albums/' + getParameterByName('album'), true);

    const name = document.getElementById('name').value;
    const artist = document.getElementById('artist').value;
    const release_date = document.getElementById('releaseDate').value;
    const score = parseFloat(document.getElementById("score").value);

    const request = {
        'name': name,
        'artist': artist,
        'releaseDate': release_date,
        'score': score
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}