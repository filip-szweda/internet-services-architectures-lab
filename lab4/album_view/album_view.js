import {
    getParameterByName,
    setLinkNode,
    setTextNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

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
    if(getParameterByName('song')) {
        xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song') + '/album/' + getParameterByName('album'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/albums/' + getParameterByName('album'))
    }
    xhttp.send();
}

function displayAlbum(album) {
    setTextNode('name', album.name);
    setTextNode('address', album.address);
    setTextNode('area', album.area);
    setTextNode('owner', album.owner)
    setLinkNode('edit', 'edit', '../album_edit/album_edit.html?song='
    + getParameterByName('song') + '&album=' + album.address)
}
