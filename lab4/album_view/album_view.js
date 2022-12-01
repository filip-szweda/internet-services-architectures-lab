import {getParameterByName, setTextNode, clearElementChildren, createButtonCell, createLinkCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const add_new_song = document.getElementById('new-element');
    add_new_song.addEventListener('click', event => displayForm(event))
    fetchAndDisplayAlbum();
    fetchAndDisplaySongs();
});

function displayForm() {
    window.location.href = '../song_add/song_add.html';
  }

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
    console.log(album.releaseDate);
    setTextNode('score', album.score);
}

function fetchAndDisplaySongs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        let songs = JSON.parse(this.responseText)
        if (songs.songs.length === 0) {
          console.log("empty collection")
          return;
        }
        displaySongs(JSON.parse(this.responseText))
      }
    };
    xhttp.open("GET", getBackendUrl() + '/api/albums/' + getParameterByName('album') + '/songs', true);
    xhttp.send();
  }
  
function displaySongs(songs) {
let tableBody = document.getElementById('tableBody');
clearElementChildren(tableBody);
songs.songs.forEach(song => {
    tableBody.appendChild(createTableRow(song.name, song.id));
})
}
  
function createTableRow(name, id) {
console.log(name)
let tr = document.createElement('tr');
tr.appendChild(createTextCell(name));
tr.appendChild(createLinkCell('view', '../song_view/song_view.html?song=' + id));
tr.appendChild(createLinkCell('edit', '../song_edit/song_edit.html?song=' + id));
tr.appendChild(createButtonCell('delete', () => deleteSong(id)));
return tr;
}
  
function deleteSong(id) {
const xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 202) {
    fetchAndDisplaySongs();
    }
};
xhttp.open("DELETE", getBackendUrl() + '/api/songs/' + id, true);
xhttp.send();
}
