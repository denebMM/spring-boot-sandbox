async function enviarPregunta() {
    const pregunta = document.getElementById("pregunta").value;
    const respuestaDiv = document.getElementById("respuesta");

    if (!pregunta.trim()) {
        respuestaDiv.innerText = "⚠️ Escribe una pregunta primero.";
        return;
    }

    try {
        const response = await fetch(`/api/chat/preguntar?q=${encodeURIComponent(pregunta)}`);
        const data = await response.text();
        respuestaDiv.innerText = data;
    } catch (error) {
        respuestaDiv.innerText = "❌ Error llamando al servidor: " + error;
    }
}
