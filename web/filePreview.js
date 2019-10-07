let id = document.getElementById.bind(document)

// OBTENGO LOS COMPONENTES POR SU ID
const archivo = id('archivo'),
        contenido = id('content'),
        redirect = id('redirect'),
        label = id('labelFile'),
        form = id('form')

// ESTA FUNCION OBTINEN LAS IMAGENES Y LAS PROCESA
const obtenerArchivo = () => {

    let imagenes = archivo.files
    
    for (let f = 0; f < imagenes.length; f++) {
        
        // ESTE OBJ ME AYUDA A PODER LEER LAS URL DE LAS IMAGENS 
        var leerArchivo = new FileReader()

        // ESTE ES UN METODO DEL OBJ ANTERIOR CON EL CUAL OBTENGO LA URL DE LA CORRESPONDIENTE IMG
        leerArchivo.readAsDataURL(imagenes[f])
        // CUANDO TODAS LAS IMG HAYAN CARGADO EJECUTA LA FUNCION VISTAPREVIA
        leerArchivo.addEventListener('load', vistaPrevia)
    }
    
}

// ESTA FUNCION RECIBE EL EVENTO EJECUTADO (LOAD)
const vistaPrevia = e => {
    
    // Y CON ESO OBTENGO LAS URL
    let url = e.target.result;
    redirect.style.display = 'block'
    label.style.display = 'none'

    // INCRUSTO LAS URL EN UN COMPONENTE DE IMG PARA POSTERIORMENTE SER MOSTRADO
    contenido.innerHTML += `
        <div class="contenedorImagen">
            <img src="${url}" class="image-items">
        </div>
    `

}

// TODO EMPEZARA CUANDO SE HAYAN AGREGADO LAS IMAGENES
archivo.addEventListener('change', obtenerArchivo)
