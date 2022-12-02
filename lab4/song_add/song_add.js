import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function createSong(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/songs/')

    const name = document.getElementById('name').value;
    const length = document.getElementById('length').value;
    const streams = parseInt(document.getElementById('streams').value);

    const request = {
        'name': name,
        'length': length,
        'streams': streams
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));

}
var highestId = 0;

function getHighestId(event) {
    const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      let songs = JSON.parse(this.responseText)
      if (songs.songs.length === 0) {
        console.log("empty collection")
        return;
      }
      checkId(event, JSON.parse(this.responseText))
    }
  };
  xhttp.open("GET", getBackendUrl() + '/api/songs/', true);
  xhttp.send();
}

function checkId(event, songs) {
    var id = 0;
    songs.songs.forEach(song => {
        if (song.id > id) {id = song.id; console.log(id);}
      })
    addToAlbum(event, id, getParameterByName('album'));
}

function addToAlbum(event, song_id, album_id) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/songs/' + song_id + '/add')
    const request = { 'id': album_id};

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}

function updateInfoAction(event) {
    createSong(event);
    getHighestId(event);
}

