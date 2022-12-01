import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    setLinkNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplaySong();
    fetchAndDisplayAlbums();
});

/**
 * Fetches all songs and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayAlbums() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAlbums(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song') + '/albums', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display albums.
 *
 * @param {{albums: {id: number, name:string}[]}} albums
 */
function displayAlbums(albums) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    albums.albums.forEach(album => {
        tableBody.appendChild(createTableRow(album));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} album
 * @returns {HTMLTableRowElement}
 */
function createTableRow(album) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(album.name));
    tr.appendChild(createLinkCell('view', '../album_view/album_view.html?song='
        + getParameterByName('song') + '&album=' + album.address));
    tr.appendChild(createLinkCell('edit', '../album_edit/album_edit.html?song='
        + getParameterByName('song') + '&album=' + album.address));
    tr.appendChild(createButtonCell('delete', () => deleteAlbum(album)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} album to be deleted
 */
function deleteAlbum(album) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAlbums();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/songs/' + getParameterByName('song')
        + '/albums/' + album.address, true);
    xhttp.send();
}


/**
 * Fetches single song and modifies the DOM tree in order to display it.
 */
function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySong(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display song.
 *
 * @param {{login: string, name: string, surname:string}} song
 */
function displaySong(song) {
    setTextNode('name', song.name);
    setTextNode('age', song.age);
    setTextNode('phone', song.phoneNumber);

    setLinkNode('add', 'ADD FARM', "../album_add/album_add.html?song=" + getParameterByName('song'));
}
