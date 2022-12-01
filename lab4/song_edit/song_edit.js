//FIXXXXXXXXX

import {getParameterByName, setTextNode} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySong();
});

/**
 * Fetches currently logged song's songs and updates edit form.
 */
function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                    setTextNode(key, value);
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);
    xhttp.send();
}

/**
 * Action event handled for updating song info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySong();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);

    const request = {
        'age': parseInt(document.getElementById('age').value),
        'phoneNumber': document.getElementById('phone').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}