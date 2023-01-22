function loadStations() {
    fetch("https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json",
        {
            headers: { "Client-Identifier": "ArdoqTest-BikeRides" }
        })

        .then(response => response.json())

        .then(function (response) {
            const stations = response.data.stations;
            // Sorting the stations alphabetical order with name
            stations.sort((a, b) => (a.name < b.name) ? -1 : (a.name > b.name) ? 1 : 0);
            const stationsSelectElement = document.getElementById("stations_select");

            for (station of stations) {
                const optionElement = document.createElement("option");
                optionElement.value = station.station_id;
                optionElement.innerHTML = station.name;
                stationsSelectElement.appendChild(optionElement);
            }
        })

        .catch(function () {
            console.error("Failed to load station status. Please try again.")
        })
}

function loadStatus() {
    fetch("https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json",
        {
            headers: { "Client-Identifier": "ArdoqTest-BikeRides" }
        })

        .then(response => response.json())

        .then(function (response) {
            const stations = response.data.stations;
            const stationsSelectElement = document.getElementById("stations_select");
            const stationId = stationsSelectElement.value;
            if (stationId != 0) {
                const stationObject = stations.find(station => station.station_id == stationId);

                const availableBikesElement = document.getElementById("available_bikes");
                const freeSpotsElement = document.getElementById("free_spots");

                availableBikesElement.innerHTML = stationObject.num_bikes_available;
                freeSpotsElement.innerHTML = stationObject.num_docks_available;

                const statusElement = document.getElementById("status");
                statusElement.classList.remove("hidden");
            }
        })

        .catch(function () {
            console.error("Failed to load station status. Please try again.");
        })
}
function loadArdoqWS() {
    var requestOptions = {
        method: 'GET',
        headers: {
            "Authorization": "Bearer 66b1adc0259c4b03a7179438473d8366",
            "X-org": "caseinterviewriya",
            "Accept": "application/json"
        },
        redirect: 'follow'
    };

    fetch("https://caseinterviewriya.ardoq.com/api/v2/workspaces", requestOptions)
        .then(response => response.json())
        .then(result => console.log(result))
        .catch(error => console.log('error', error))

        .then(function (response) {
            const stations = response.values[0];
            // Sorting the stations alphabetical order with name
            const WSSelectElement = document.getElementById("WS_select");
            const optionElement = document.createElement("option");
            optionElement.value = stations._id;
            optionElement.innerHTML = stations.name;
            WSSelectElement.appendChild(optionElement);

        })


        .catch(function () {
            console.error("Failed to load workspaces. Please try again.")
        })
}
function loadMyWorkspace() {
    var reqHeaders = new Headers();
    reqHeaders.append("Accept", "application/json");
    reqHeaders.append("X-org", "caseinterviewriya");
    reqHeaders.append("Authorization", "Bearer 66b1adc0259c4b03a7179438473d8366");
    reqHeaders.append("Access-Control-Allow-Origin", "*");
    alert(" Well, you are fetching data from ARdoq via Ardoq API V2. Click OK to continue. ");
    var requestOptions = {
        method: 'GET',
        headers: reqHeaders,
        redirect: 'follow'
    };

    fetch("https://caseinterviewriya.ardoq.com/api/v2/workspaces", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error))

        .then(response => response.json())
        .then(data => ({ status: response.status, body: data }))
        .then(obj => console.log(obj))

        .catch(function () {
            console.error("Failed to load workspaces. Please try again.")
        })
}

//Load Workspaces on page load
loadArdoqWS();

//Load Workspace details on selection
document.getElementById("WS_select").addEventListener("click", loadMyWorkspace);

// Load stations on page load
loadStations();

// Auto-refresh status every 10 seconds
setInterval(loadStatus, 10000);

// Loads status when changing station in dropdown
document.getElementById("stations_select").addEventListener("change", loadStatus);