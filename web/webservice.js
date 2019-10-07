/*
 * ESTE DOCUMENTO JS ES SOLO PARA RECIBIR LA PETICION
 * Y MOSTRAR LAS IMAGENES RECIBIDAS
 * 
 */

let id = document.getElementById.bind(document)

const archivo = id('archivo'),
        contenido = id('content')

// PETICION AL SERVIDOR
addEventListener("load", peticion);

function peticion() {
    fetch('imagenes')
            .then(response => response.json())
            .then(data => {
                
                mostrarImagenes(data)
            }
    )
            .catch(e => {
                console.log("Hubo algo " + e)
            })
}


function mostrarImagenes(datos){
    
    contenido.innerHTML = ""
    
    for(let imagen of datos){
        contenido.innerHTML += `
            <div class="contenedorImagen">
                <img src="${imagen.imagen1}" class="image-items">
            </div>
            <div class="contenedorImagen">
                <img src="${imagen.imagen2}" class="image-items">
            </div>
            <div class="contenedorImagen">
                <img src="${imagen.imagen3}" class="image-items">
            </div>
            <div class="contenedorImagen">
                <img src="${imagen.imagen4}" class="image-items">
            </div>
            <div class="contenedorImagen">
                <img src="${imagen.imagen5}" class="image-items">
            </div>
            `
    }
}

