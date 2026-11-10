// ==========================================================================
// CONFIGURACIÓN DE ENDPOINTS (API REST DE SPRING BOOT)
// ==========================================================================
const API_INMUEBLES = 'http://localhost:8080/api/inmuebles';
const API_SOLICITUDES = 'http://localhost:8080/api/solicitudes';

// ==========================================================================
// 1. INICIALIZACIÓN DE LA PÁGINA
// ==========================================================================
document.addEventListener('DOMContentLoaded', () => {
    cargarPropiedadesDestacadas();
    inicializarCarruselVideos();
});

// ==========================================================================
// 2. FUNCIÓN PARA CARGAR Y RENDERIZAR LAS PROPIEDADES (GET - CARRUSEL)
// ==========================================================================
async function cargarPropiedadesDestacadas() {
    const contenedorGrid = document.getElementById('gridPropiedades');

    if (!contenedorGrid) {
        console.error('No se encontró el contenedor gridPropiedades en el HTML.');
        return;
    }

    try {
        const respuesta = await fetch(API_INMUEBLES);

        if (!respuesta.ok) {
            throw new Error(`Error en el servidor: HTTP ${respuesta.status}`);
        }

        const inmuebles = await respuesta.json();

        if (!inmuebles || inmuebles.length === 0) {
            contenedorGrid.innerHTML = `<p class="texto-cargando">No hay propiedades destacadas disponibles en este momento.</p>`;
            return;
        }

        contenedorGrid.innerHTML = '';

        inmuebles.forEach(inmueble => {
            const tarjeta = document.createElement('div');
            tarjeta.classList.add('tarjeta-propiedad-item'); // Clase adaptada al carrusel

            const titulo = inmueble.titulo || 'Propiedad en Gestión';
            const precio = inmueble.precio || 'Consultar';
            const descripcion = inmueble.descripcion || 'Inmueble administrado bajo respaldo jurídico.';
            const idInmueble = inmueble.idInmueble || inmueble.id || 1;

            tarjeta.innerHTML = `
                <h3>${titulo}</h3>
                <div class="precio-prop">$ ${precio}</div>
                <p>${descripcion}</p>
                <button type="button" onclick="abrirModal(${idInmueble}, '${titulo}')" class="enlace-servicio" style="background:none; border:none; cursor:pointer; color:#ff7300; font-weight:600; padding:0; text-align:left;">
                    Consultar Inmueble →
                </button>
            `;

            contenedorGrid.appendChild(tarjeta);
        });

    } catch (error) {
        console.error('Error al cargar propiedades:', error);
        contenedorGrid.innerHTML = `<p class="texto-cargando" style="color: #ff4d4d;">Error al conectar con el servidor. Verifica que Spring Boot esté activo.</p>`;
    }
}

// Función global para desplazar el carrusel de propiedades con las flechas laterales
function desplazarPropiedades(direccion) {
    const track = document.getElementById('gridPropiedades');
    if (!track) return;

    const tarjetaAncho = 320; // Ancho de la tarjeta (300px) + gap (20px aprox)
    track.scrollBy({
        left: direccion * tarjetaAncho,
        behavior: 'smooth'
    });
}

// ==========================================================================
// 3. FUNCIONES DE CONTROL DEL MODAL (INTERFAZ)
// ==========================================================================
function abrirModal(idInmueble, tituloInmueble) {
    const inputId = document.getElementById('modalIdInmueble');
    const subTitulo = document.getElementById('subtituloModal');
    const mensajeInput = document.getElementById('mensajeCliente');
    const modal = document.getElementById('modalContacto');

    if (inputId) inputId.value = idInmueble || '';
    if (subTitulo) subTitulo.innerText = `Consulta sobre: ${tituloInmueble}`;
    if (mensajeInput) {
        mensajeInput.value = `Hola, estoy interesado en agendar una visita/asesoría para la propiedad: ${tituloInmueble}`;
    }
    if (modal) modal.style.display = 'flex';
}

function cerrarModal() {
    const modal = document.getElementById('modalContacto');
    if (modal) modal.style.display = 'none';
}

// ==========================================================================
// 4. PERSISTENCIA EN BASE DE DATOS Y REDIRECCIÓN A WHATSAPP (POST)
// ==========================================================================
async function procesarSolicitud(event) {
    event.preventDefault();

    const idInmuebleVal = document.getElementById('modalIdInmueble').value;
    const nombre = document.getElementById('nombreCliente').value;
    const telefono = document.getElementById('telefonoCliente').value;
    const mensaje = document.getElementById('mensajeCliente').value;

    const datosSolicitud = {
        inmueble: idInmuebleVal ? { idInmueble: parseInt(idInmuebleVal) } : null,
        nombreInteresado: nombre,
        telefonoInteresado: telefono,
        correoInteresado: null, 
        mensaje: mensaje
    };

    try {
        const respuesta = await fetch(API_SOLICITUDES, {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(datosSolicitud)
        });

        if (!respuesta.ok) {
            throw new Error(`Error en la API: HTTP status ${respuesta.status}`);
        }

        const resultadoBD = await respuesta.json();
        console.log('Registro exitoso guardado en MySQL:', resultadoBD);

    } catch (error) {
        console.error('Error al intentar persistir en la base de datos:', error);
    } finally {
        const mensajeWa = `Hola Lente Inmobiliario, mi nombre es ${nombre} (Tel: ${telefono}). ${mensaje}`;
        const urlWhatsapp = `https://api.whatsapp.com/send/?phone=573228185272&text=${encodeURIComponent(mensajeWa)}&type=phone_number&app_absent=0`;

        cerrarModal();
        
        const form = document.getElementById('formSolicitudContacto');
        if (form) form.reset();

        window.open(urlWhatsapp, '_blank');
    }
}

// ==========================================================================
// 5. CONTROL DEL CARRUSEL HERO (PRINCIPAL)
// ==========================================================================
let slideActual = 0;

function mostrarSlide(n) {
    const slides = document.getElementsByClassName("carrusel-slide");
    const puntos = document.getElementsByClassName("punto");

    if (!slides.length) return;

    if (n >= slides.length) {
        slideActual = 0;
    } else if (n < 0) {
        slideActual = slides.length - 1;
    } else {
        slideActual = n;
    }

    for (let i = 0; i < slides.length; i++) {
        slides[i].classList.remove("active");
    }
    for (let i = 0; i < puntos.length; i++) {
        puntos[i].classList.remove("active");
    }

    slides[slideActual].classList.add("active");
    if (puntos[slideActual]) {
        puntos[slideActual].classList.add("active");
    }
}

function moverSlide(n) {
    mostrarSlide(slideActual + n);
}

function irSlide(n) {
    mostrarSlide(n);
}

setInterval(() => {
    moverSlide(1);
}, 10000);

// ==========================================================================
// 6. CONTROL DE DESPLAZAMIENTO, BUCLE Y PUNTOS (CÁPSULAS YOUTUBE - FIJO A 3 PÁGINAS)
// ==========================================================================
let paginaActual = 0;
const totalPaginasCalc = 2; // 0, 1 y 2 (Exactamente 3 bolitas)

function inicializarCarruselVideos() {
    const track = document.getElementById('videoTrack');
    const dotsContainer = document.getElementById('videoDots');
    
    if (!track || !dotsContainer) return;

    const tarjetas = track.querySelectorAll('.tarjeta-video-short');
    if (!tarjetas.length) return;

    dotsContainer.innerHTML = ''; // Limpiar contenedor de puntos

    // Crear exactamente 3 bolitas fijas
    for (let i = 0; i <= totalPaginasCalc; i++) {
        const dot = document.createElement('div');
        dot.classList.add('dot');
        if (i === 0) dot.classList.add('active');
        
        dot.addEventListener('click', () => {
            paginaActual = i;
            moverApagina(paginaActual);
        });
        dotsContainer.appendChild(dot);
    }

    // Actualizar punto activo según el scroll manual del usuario
    track.addEventListener('scroll', () => {
        const dots = dotsContainer.querySelectorAll('.dot');
        if (!dots.length) return;

        const maxScroll = track.scrollWidth - track.clientWidth;
        if (maxScroll <= 0) return;

        // Mapea la posición actual del scroll a una de las 3 páginas (0, 1 o 2)
        const porcentajeScroll = track.scrollLeft / maxScroll;
        paginaActual = Math.round(porcentajeScroll * totalPaginasCalc);
        
        dots.forEach((dot, i) => {
            if (i === paginaActual) {
                dot.classList.add('active');
            } else {
                dot.classList.remove('active');
            }
        });
    });
}

// Función auxiliar para mover el carrusel basado en la página actual (0, 1 o 2)
function moverApagina(pagina) {
    const track = document.getElementById('videoTrack');
    if (!track) return;

    const maxScroll = track.scrollWidth - track.clientWidth;
    const destino = (maxScroll / totalPaginasCalc) * pagina;

    track.scrollTo({
        left: destino,
        behavior: 'smooth'
    });
}

// Función global sincronizada para las flechas laterales con bucle infinito real (3 pasos)
function desplazarVideos(direccion) {
    const track = document.getElementById('videoTrack');
    if (!track) return;

    paginaActual += direccion;

    // Bucle infinito circular entre las 3 páginas
    if (paginaActual > totalPaginasCalc) {
        paginaActual = 0; // Si está en la última y da siguiente, regresa a la primera
    } else if (paginaActual < 0) {
        paginaActual = totalPaginasCalc; // Si está en la primera y da atrás, salta a la última
    }

    moverApagina(paginaActual);
}

// ==========================================================================
// 7. REPRODUCCIÓN EN TARJETA (SIN DESBORDE DE BORDES)
// ==========================================================================
function reproducirEnTarjeta(idVideo) {
    const contenedor = document.getElementById(`container-${idVideo}`);
    if (!contenedor) return;

    contenedor.innerHTML = `
        <iframe 
            src="https://www.youtube.com/embed/${idVideo}?autoplay=1&rel=0" 
            title="YouTube Short" 
            style="width:100%; height:100%; border:none; border-radius:14px 14px 0 0; display:block;"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
        </iframe>
    `;
}