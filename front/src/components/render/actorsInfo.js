import {useEffect, useState} from 'react';
import {createNewActor, getActorsInfo, deleteActor, sendMessageActor} from '../../api/apiService';
import BootstrapTable from 'react-bootstrap-table-next';
import '../../styles/commonStyles.css';
import Progressbar from './progress_bar';



export default function GetActorsInfo(props) {
    const [actors, setActors] = useState([]);
    const [type, setType] = useState("HelloWorldActor");
    const [name, setName] = useState("");
    const [message, setMessage] = useState("");
    const [dest, setDest] = useState("");
    const columns = [
        {
            dataField: "actorName",
            text: "Actor name",
        },
        {
            dataField: "messagesQueue",
            text: "Message in the queue",
        },
        { dataField: 'messagesProcessed', text: 'Messages processed'},
        { dataField: 'status', text: 'Status'},
        { dataField: 'stop', text: 'Stop the process'}
    ];

    function stopActorClickFunction(name) {
        return stopActor(name);
    }
    async function stopActor(name) {
        await deleteActor(name)
        await getData();
    }

    async function createActor(e) {
        e.preventDefault();
        if (name !== "" && type !== "") {
            createNewActor(name, type)
            await getData();
        }
    }

    function onChangeName(event) {
        event.preventDefault()
        setName(event.target.value)
    }

    function onChangeType(event) {
        event.preventDefault()
        setType(event.target.value)
    }

    function onChangeDest(event) {
        event.preventDefault()
        setDest(event.target.value)
    }

    function onChangeMessage(event) {
        event.preventDefault()
        setMessage(event.target.value)
    }

    async function getData() {
        let actors = await getActorsInfo();
        actors = actors.actors;
        for (var i = 0; i < actors.length; i++) {
            let nameActor = actors[i].actorName;
            let messagesProcessed = actors[i].messagesProcessed;
            if (messagesProcessed > 100) messagesProcessed = 100;
            actors[i].messagesProcessed = <Progressbar bgcolor="orange" progress={messagesProcessed}  height={30} />;
            actors[i].stop =
                <button onClick={() => stopActorClickFunction(nameActor)}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                    </svg>
                </button>
        }
        setActors(actors);

    }
    async function sendMessage(e) {
        e.preventDefault()
        if (message !== "" && dest !== ""){
            sendMessageActor(dest, message);
            await getData();
        }

    }
    function useFetch() {
        useEffect(() => {
            getData();
        }, [])

        useEffect(() => {
            async function updateData() {
                getData();

            }
            const interval = setInterval(() => {
                updateData()
            }, 100);

            return () => clearInterval(interval);
        }, []);
        return (
            <>
                <label htmlFor="name"></label>
                <input type="text" id="name" name="name" placeholder="Nombre para el actor" onChange={(e) => onChangeName(e)}/>
                <select id="type" name="type" onChange={(e) => onChangeType(e)}>
                    <option value="HelloWorldActor">HelloWorldActor</option>
                    <option value="RingActor">RingActor</option>
                    <option value="PingPongActor">PingPongActor</option>
                    <option value="InsultActor">InsultActor</option>
                </select>
                <input type="submit" value="Crear actor" onClick={(e) => createActor(e)} />

                <form onClick={(e) => sendMessage(e)}>
                    <label htmlFor="message"></label>
                    <input type="text" id="message" name="message" placeholder="Mensaje a enviar" onChange={(e) => onChangeMessage(e)}/>
                    <input type="text" id="dest" name="dest" placeholder="Nombre del destinatario..." onChange={(e) => onChangeDest(e)}/>
                    <input type="submit" value="Enviar mensaje"/>
                </form>
                <BootstrapTable keyField='actorName' data={actors} columns={columns}/>
            </>
        );
    }

    return (
        <div>
            {useFetch()}
        </div>
    )
}