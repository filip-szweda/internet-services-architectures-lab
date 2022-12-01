import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayAlbum();
});

/**
 * Fetches single song and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayAlbum() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAlbum(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/albums/' + getParameterByName('album'))
    xhttp.send();
}

function displayAlbum(album) {
    setTextNode('name', album.name);
    setTextNode('artist', album.artist);
    setTextNode('releaseDate', album.releaseDate);
    setTextNode('score', album.score);
}
