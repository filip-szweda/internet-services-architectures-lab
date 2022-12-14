import {clearElementChildren, createButtonCell, createLinkCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
  const add_new_album = document.getElementById('new-category');
  add_new_album.addEventListener('click', event => displayForm(event))
  fetchAndDisplayAlbums();
});

function displayForm() {
  window.location.href = '../album_add/album_add.html';
}

function fetchAndDisplayAlbums() {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      let albums = JSON.parse(this.responseText)
      if (albums.albums.length === 0) {
        console.log("empty collection")
        return;
      }
      displayAlbums(JSON.parse(this.responseText))
    }
  };
  xhttp.open("GET", getBackendUrl() + '/api/albums/', true);
  xhttp.send();
}

function displayAlbums(albums) {
  let tableBody = document.getElementById('tableBody');
  clearElementChildren(tableBody);
  albums.albums.forEach(album => {
    tableBody.appendChild(createTableRow(album.name, album.id));
  })
}

function createTableRow(name, id) {
  console.log(name)
  let tr = document.createElement('tr');
  tr.appendChild(createTextCell(name));
  tr.appendChild(createLinkCell('view', '../album_view/album_view.html?album=' + id));
  tr.appendChild(createLinkCell('edit', '../album_edit/album_edit.html?album=' + id));
  tr.appendChild(createButtonCell('delete', () => deleteAlbum(id)));
  return tr;
}

function deleteAlbum(id) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 202) {
      fetchAndDisplayAlbums();
    }
  };
  xhttp.open("DELETE", getBackendUrl() + '/api/albums/' + id, true);
  xhttp.send();
}