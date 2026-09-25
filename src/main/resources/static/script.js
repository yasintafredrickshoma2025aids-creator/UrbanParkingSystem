// ==========================================
// SMART PARKING COMMAND CENTER
// Frontend connected to Spring Boot backend
// ==========================================


// ==========================================
// DASHBOARD DATA
// ==========================================

async function loadDashboard() {

    try {

        const vehiclesResponse =
            await fetch("/vehicles");

        const slotsResponse =
            await fetch("/parking-slots");

        const shuttlesResponse =
            await fetch("/robotic-shuttles");


        if (!vehiclesResponse.ok ||
            !slotsResponse.ok ||
            !shuttlesResponse.ok) {

            throw new Error("Unable to load dashboard data");
        }


        const vehicles =
            await vehiclesResponse.json();

        const slots =
            await slotsResponse.json();

        const shuttles =
            await shuttlesResponse.json();


        // ==========================================
        // CALCULATE DASHBOARD VALUES
        // ==========================================

        const totalVehicles =
            vehicles.length;

        const occupiedSlots =
            slots.filter(function (slot) {

                return slot.status &&
                    slot.status.toUpperCase() === "OCCUPIED";

            }).length;


        const availableSlots =
            slots.filter(function (slot) {

                return slot.status &&
                    slot.status.toUpperCase() === "AVAILABLE";

            }).length;


        const activeShuttles =
            shuttles.filter(function (shuttle) {

                return shuttle.status &&
                    shuttle.status.toUpperCase() === "ACTIVE";

            }).length;


        // ==========================================
        // UPDATE DASHBOARD CARDS
        // ==========================================

        const totalVehiclesElement =
            document.getElementById("totalVehicles");

        const occupiedSlotsElement =
            document.getElementById("occupiedSlots");

        const availableSlotsElement =
            document.getElementById("availableSlots");

        const activeShuttlesElement =
            document.getElementById("activeShuttles");


        if (totalVehiclesElement) {

            totalVehiclesElement.textContent =
                totalVehicles;
        }


        if (occupiedSlotsElement) {

            occupiedSlotsElement.textContent =
                occupiedSlots;
        }


        if (availableSlotsElement) {

            availableSlotsElement.textContent =
                availableSlots;
        }


        if (activeShuttlesElement) {

            activeShuttlesElement.textContent =
                activeShuttles;
        }


        // ==========================================
        // DISPLAY PARKING SLOTS
        // ==========================================

        const parkingSlotsElement =
            document.getElementById("parkingSlots");


        if (parkingSlotsElement) {

            parkingSlotsElement.innerHTML = "";


            slots.forEach(function (slot) {

                const status =
                    slot.status
                        ? slot.status.toUpperCase()
                        : "UNKNOWN";


                const div =
                    document.createElement("div");


                if (status === "OCCUPIED") {

                    div.className =
                        "slot occupied";

                } else {

                    div.className =
                        "slot available";
                }


                div.innerHTML =
                    slot.slotNumber +
                    "<small>" +
                    status +
                    "</small>";


                parkingSlotsElement.appendChild(div);

            });
        }


        // ==========================================
        // DISPLAY ROBOTIC SHUTTLES
        // ==========================================

        const roboticShuttlesElement =
            document.getElementById("roboticShuttles");


        if (roboticShuttlesElement) {

            roboticShuttlesElement.innerHTML = "";


            shuttles.forEach(function (shuttle) {

                const div =
                    document.createElement("div");


                div.className =
                    "robot";


                div.innerHTML =

                    "<div class='robot-header'>" +

                    "<div class='robot-name'>" +
                    "🤖 " +
                    (shuttle.shuttleName ||
                        "Shuttle") +
                    "</div>" +

                    "<div class='robot-status'>" +
                    "● " +
                    (shuttle.status ||
                        "UNKNOWN") +
                    "</div>" +

                    "</div>" +

                    "<div class='robot-info'>" +

                    "Manufacturer: " +
                    (shuttle.manufacturer ||
                        "N/A") +

                    "<br>" +

                    "Speed Profile: " +
                    (shuttle.speedProfile ||
                        "N/A") +

                    "<br>" +

                    "Status: " +
                    (shuttle.status ||
                        "UNKNOWN") +

                    "</div>";


                roboticShuttlesElement
                    .appendChild(div);

            });
        }


        console.log(
            "Dashboard loaded successfully"
        );


    } catch (error) {

        console.error(
            "Dashboard error:",
            error
        );
    }
}


// ==========================================
// SIDEBAR MENU
// ==========================================

function showMenu(name) {

    alert(
        name +
        "\n\nThis module will be connected to the backend."
    );

}


// ==========================================
// REGISTER VEHICLE
// ==========================================

async function registerVehicle() {

    const registrationNumber =
        prompt(
            "Enter vehicle registration number:"
        );


    if (!registrationNumber) {

        return;
    }


    const vehicleType =
        prompt(
            "Enter vehicle type:\n\n" +
            "CAR\n" +
            "SUV\n" +
            "BIKE"
        );


    if (!vehicleType) {

        return;
    }


    const ownerName =
        prompt(
            "Enter vehicle owner name:"
        );


    if (!ownerName) {

        return;
    }


    const vehicleData = {

        registrationNumber:
        registrationNumber,

        vehicleType:
        vehicleType,

        ownerName:
        ownerName

    };


    try {

        const response =
            await fetch(
                "/vehicles",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(
                            vehicleData
                        )
                }
            );


        if (!response.ok) {

            throw new Error(
                "Vehicle registration failed"
            );
        }


        const vehicle =
            await response.json();


        alert(

            "VEHICLE REGISTERED SUCCESSFULLY\n\n" +

            "Vehicle ID: " +
            vehicle.id +

            "\nRegistration: " +
            vehicle.registrationNumber +

            "\nVehicle Type: " +
            vehicle.vehicleType +

            "\nOwner: " +
            vehicle.ownerName

        );


        await loadDashboard();


    } catch (error) {

        console.error(error);


        alert(

            "Unable to register vehicle.\n\n" +
            "Please make sure Spring Boot is running."

        );
    }
}


// ==========================================
// PARK VEHICLE
// ==========================================

async function parkVehicle() {

    const vehicleId =
        prompt(
            "Enter Vehicle ID to park:"
        );


    if (!vehicleId) {

        return;
    }


    try {

        const response =
            await fetch(
                "/parking-sessions/entry/" +
                vehicleId,
                {
                    method: "POST"
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            throw new Error(
                errorText ||
                "Parking entry failed"
            );
        }


        const session =
            await response.json();


        alert(

            "VEHICLE PARKED SUCCESSFULLY\n\n" +

            "Vehicle ID: " +
            session.vehicleId +

            "\nParking Slot ID: " +
            session.parkingSlotId +

            "\nStatus: " +
            session.status +

            "\nRetrieval Code: " +
            session.retrievalCode

        );


        await loadDashboard();


    } catch (error) {

        console.error(error);


        alert(

            "Unable to park vehicle.\n\n" +
            "Make sure the vehicle exists and an available parking slot exists."

        );
    }
}


// ==========================================
// RETRIEVE VEHICLE
// ==========================================

async function retrieveVehicle() {

    const vehicleId =
        prompt(
            "Enter Vehicle ID to retrieve:"
        );


    if (!vehicleId) {

        return;
    }


    try {

        const response =
            await fetch(
                "/parking-sessions/exit/" +
                vehicleId,
                {
                    method: "POST"
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            throw new Error(
                errorText ||
                "Vehicle retrieval failed"
            );
        }


        const session =
            await response.json();


        alert(

            "VEHICLE RETRIEVED SUCCESSFULLY\n\n" +

            "Vehicle ID: " +
            session.vehicleId +

            "\nParking Slot ID: " +
            session.parkingSlotId +

            "\nDuration: " +
            session.durationMinutes +
            " minutes" +

            "\nParking Fee: ₹" +
            session.totalAmount +

            "\nStatus: " +
            session.status +

            "\nRetrieval Code: " +
            session.retrievalCode

        );


        await loadDashboard();


    } catch (error) {

        console.error(error);


        alert(

            "Unable to retrieve vehicle.\n\n" +
            "Make sure the vehicle currently has an active parking session."

        );
    }
}


// ==========================================
// BILLING
// ==========================================

async function viewBilling() {

    try {

        const response =
            await fetch(
                "/parking-invoices"
            );


        if (!response.ok) {

            throw new Error(
                "Unable to load invoices"
            );
        }


        const invoices =
            await response.json();


        if (invoices.length === 0) {

            alert(
                "No parking invoices found."
            );

            return;
        }


        let message =
            "PARKING BILLING\n\n";


        invoices.forEach(
            function (invoice) {

                message +=
                    "Invoice ID: " +
                    invoice.id +

                    "\nVehicle ID: " +
                    invoice.vehicleId +

                    "\nSession ID: " +
                    invoice.parkingSessionId +

                    "\nAmount: ₹" +
                    invoice.amount +

                    "\nPayment Status: " +
                    invoice.paymentStatus +

                    "\n\n";

            }
        );


        alert(message);


    } catch (error) {

        console.error(error);


        alert(
            "Unable to load billing information."
        );
    }
}


// ==========================================
// PAGE LOAD
// ==========================================

document.addEventListener(
    "DOMContentLoaded",
    function () {

        loadDashboard();

    }
);


// ==========================================
// AUTO REFRESH
// ==========================================

setInterval(
    function () {

        loadDashboard();

    },
    10000
);