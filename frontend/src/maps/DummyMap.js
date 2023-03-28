import React from "react"

const territories = [
    { name: 'Rottweiler', owner: 'Green', units: 8 },
    { name: 'Dachshund', owner: 'Green', units: 5 },
    { name: 'Beagle', owner: 'Green', units: 2 },
    { name: 'Labrador', owner: 'Green', units: 3 },
    { name: 'Poodle', owner: 'Green', units: 6 },
    { name: 'Bulldog', owner: 'Green', units: 4 },
    { name: 'Boxer', owner: 'Red', units: 10 },
    { name: 'Havanese', owner: 'Red', units: 1 },
    { name: 'Spaniel', owner: 'Red', units: 7 },
    { name: 'Sheepdog', owner: 'Red', units: 2 },
    { name: 'Akita', owner: 'Red', units: 9 },
    { name: 'Brittany', owner: 'Red', units: 6 },
    { name: 'Vizsla', owner: 'Blue', units: 5 },
    { name: 'Maltese', owner: 'Blue', units: 3 },
    { name: 'Chihuahua', owner: 'Blue', units: 8 },
    { name: 'Pug', owner: 'Blue', units: 2 },
    { name: 'Mastiff', owner: 'Blue', units: 7 },
    { name: 'Collie', owner: 'Blue', units: 9 },
    { name: 'Dalmatian', owner: 'Yellow', units: 4 },
    { name: 'Papillon', owner: 'Yellow', units: 6 },
    { name: 'Setter', owner: 'Yellow', units: 2 },
    { name: 'Samoyed', owner: 'Yellow', units: 8 },
    { name: 'Bullmastiff', owner: 'Yellow', units: 3 },
    { name: 'Whippet', owner: 'Yellow', units: 1 },
];

const territoryStyles = {
    width: '100px',
    height: '100px',
    color: 'white',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: '20px',
    fontWeight: 'bold',
    margin: '10px'
};

const DummyMap = () => (
    <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {territories.map(territory => (
            <div key={territory.name} style={{ ...territoryStyles, backgroundColor: territory.owner.toLowerCase() }}>
                {territory.name}
            </div>
        ))}
    </div >
);

export default DummyMap;