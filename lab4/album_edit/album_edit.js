import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayAlbum();
});

/**
 * Fetches currently logged song's albums and updates edit form.
 */
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
    if(getParameterByName('song')) {
        xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song') + '/albums/' + getParameterByName('album'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/albums/' + getParameterByName('album'))
    }
    xhttp.send();
}

/**
 * Action event handled for updating album info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayAlbum();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/songs/' + getParameterByName('song') + '/albums/'
        + getParameterByName('album'), true);

    const request = {
        'name': document.getElementById('name').value,
        'area': parseInt(document.getElementById('area').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}