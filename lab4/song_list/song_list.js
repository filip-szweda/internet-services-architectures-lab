import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplaySongs();
});

/**
 * Fetches all songs and modifies the DOM tree in order to display them.
 */
function fetchAndDisplaySongs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySongs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/songs', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display songs.
 *
 * @param {{songs: string[]}} songs
 */
function displaySongs(songs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    songs.songs.forEach(song => {
        tableBody.appendChild(createTableRow(song));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} song
 * @returns {HTMLTableRowElement}
 */
function createTableRow(song) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(song["name"]));
    tr.appendChild(createLinkCell('view', '../song_view/song_view.html?song=' + song["name"]));
    tr.appendChild(createLinkCell('edit', '../song_edit/song_edit.html?song=' + song["name"]));
    tr.appendChild(createButtonCell('delete', () => deleteSong(song)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } song to be deleted
 */
function deleteSong(song) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySongs();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/songs/' + song["name"], true);
    xhttp.send();
}
