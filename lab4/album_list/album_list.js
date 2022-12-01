import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayAlbums();
});

/**
 * Fetches all albums and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayAlbums() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayAlbums(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/albums', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display albums.
 *
 * @param {{albums: string[]}} albums
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
 * @param {string} album
 * @returns {HTMLTableRowElement}
 */
function createTableRow(album) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(album["name"]));
    tr.appendChild(createLinkCell('view', '../album_view/album_view.html?album=' + album["address"]));
    tr.appendChild(createLinkCell('edit', '../album_edit/album_edit.html?album=' + album["address"]));
    tr.appendChild(createButtonCell('delete', () => deleteAlbum(album)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } album to be deleted
 */
function deleteAlbum(album) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayAlbums();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/albums/' + album["address"], true);
    xhttp.send();
}
