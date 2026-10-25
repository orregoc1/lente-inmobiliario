// ==========================================================================
// CONFIGURACIÓN DE ENDPOINTS (API REST DE SPRING BOOT)
// ==========================================================================
const API_INMUEBLES = 'http://localhost:8080/api/inmuebles';
const API_SOLICITUDES = 'http://localhost:8080/api/solicitudes';

// ==========================================================================
// 1. INICIALIZACIÓN DE LA PÁGINA
// ==========================================================================
// Escuchamos cuando el DOM de la página web termina de cargar
document.addEventListener('DOMContentLoaded', () => {
    cargarPropiedadesDestacadas();
});

// ==========================================================================
// 2. FUNCIÓN PARA CARGAR Y RENDERIZAR LAS PROPIEDADES (GET)
// ==========================================================================
async function cargarPropiedadesDestacadas() {
    const contenedorGrid = document.getElementById('gridPropiedades');

    if (!contenedorGrid) {
        console.error('No se encontró el contenedor gridPropiedades en el HTML.');
        return;
    }

    try {
        // Petición GET a Spring Boot para traer los inmuebles desde MySQL
        const respuesta = await fetch(API_INMUEBLES);

        if (!respuesta.ok) {
            throw new Error(`Error en el servidor: HTTP ${respuesta.status}`);
        }

        const inmuebles = await respuesta.json();

        // Si no hay propiedades registradas
        if (!inmuebles || inmuebles.length === 0) {
            contenedorGrid.innerHTML = `<p class="texto-cargando">No hay propiedades destacadas disponibles en este momento.</p>`;
            return;
        }

        // Limpiamos el texto de "Cargando..."
        contenedorGrid.innerHTML = '';

        // Recorremos cada inmueble para crear su tarjeta visual
        inmuebles.forEach(inmueble => {
            const tarjeta = document.createElement('div');
            tarjeta.classList.add('tarjeta-servicio');

            // Extraemos los datos del inmueble de forma segura
            const titulo = inmueble.titulo || 'Propiedad en Gestión';
            const precio = inmueble.precio || 'Consultar';
            const descripcion = inmueble.descripcion || 'Inmueble administrado bajo respaldo jurídico.';
            const idInmueble = inmueble.idInmueble || inmueble.id || 1;

            // Construimos el HTML de la tarjeta con el botón que abre el modal
            tarjeta.innerHTML = `
                <h3>${titulo}</h3>
                <p><strong>Precio:</strong> $ ${precio}</p>
                <p>${descripcion}</p>
                <button type="button" onclick="abrirModal(${idInmueble}, '${titulo}')" class="enlace-servicio" style="background:none; border:none; cursor:pointer; font-size:inherit; padding:0;">
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

// ==========================================================================
// 3. FUNCIONES DE CONTROL DEL MODAL (INTERFAZ)
// ==========================================================================

/**
 * Abre la ventana modal y precarga la información del inmueble seleccionado.
 */
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

/**
 * Oculta la ventana modal.
 */
function cerrarModal() {
    const modal = document.getElementById('modalContacto');
    if (modal) modal.style.display = 'none';
}

// ==========================================================================
// 4. PERSISTENCIA EN BASE DE DATOS Y REDIRECCIÓN A WHATSAPP (POST)
// ==========================================================================
async function procesarSolicitud(event) {
    // Evitamos que el formulario recargue la página por defecto
    event.preventDefault();

    // Capturamos los valores ingresados en el modal
    const idInmuebleVal = document.getElementById('modalIdInmueble').value;
    const nombre = document.getElementById('nombreCliente').value;
    const telefono = document.getElementById('telefonoCliente').value;
    const mensaje = document.getElementById('mensajeCliente').value;

    // Construimos el objeto JSON haciendo MATCH EXACTO con tu Entidad Java SolicitudContacto:
    // - inmueble se envía como un objeto con su ID (relación ManyToOne)
    // - nombreInteresado y telefonoInteresado coinciden con los campos de tu clase Java
    // - correoInteresado va en null ya que el modal no lo pide
    const datosSolicitud = {
        inmueble: idInmuebleVal ? { idInmueble: parseInt(idInmuebleVal) } : null,
        nombreInteresado: nombre,
        telefonoInteresado: telefono,
        correoInteresado: null, 
        mensaje: mensaje
    };

    console.log('1. Enviando datos hacia el backend Spring Boot...', datosSolicitud);

    try {
        // Petición POST al controlador de Spring Boot para guardar en MySQL
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
        console.log('2. ¡Registro exitoso guardado en la tabla solicitudes_contacto de MySQL!', resultadoBD);

    } catch (error) {
        console.error('Error al intentar persistir en la base de datos:', error);
    } finally {
        // Bloque final: Se ejecuta siempre para abrir WhatsApp sin bloquear al usuario
        const mensajeWa = `Hola Lente Inmobiliario, mi nombre es ${nombre} (Tel: ${telefono}). ${mensaje}`;
        const urlWhatsapp = `https://api.whatsapp.com/send/?phone=573228185272&text=${encodeURIComponent(mensajeWa)}&type=phone_number&app_absent=0`;

        cerrarModal();
        
        const form = document.getElementById('formSolicitudContacto');
        if (form) form.reset();

        window.open(urlWhatsapp, '_blank');
    }
}