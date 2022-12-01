import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    UpdateOwnerField();

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

/**
 * Action event handled for updating album info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            location.replace("../song_view/song_view.html?song=" + getParameterByName("song"));
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/albums/')

    const request = {
        'name': document.getElementById('name').value,
        'area': parseInt(document.getElementById('area').value),
        'address': document.getElementById('address').value,
        'owner': getParameterByName("song")
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function UpdateOwnerField() {
    document.getElementById('owner').value = getParameterByName("song");
}