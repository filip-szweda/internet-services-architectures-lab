import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySong();
});

function displayForm() {
    window.location.href = '../song_add/song_add.html';
  }

function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySong(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'))
    xhttp.send();
}

function displaySong(song) {
    setTextNode('name', song.name);
    setTextNode('length', song.length);
    setTextNode('streams', song.streams);
}
