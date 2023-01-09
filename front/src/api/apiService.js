const apiUrl = 'http://localhost:9000';

const requestMethods = { post: 'POST', put: 'PUT', get: 'GET'};

const methods = {
    listActors: 'ListActors',
    createActor: 'CreateActor',
    deleteActor: 'DeleteActor',
    sendMessage: 'SendMessage',
    getActorInfo: 'actorInfo'
}
async function fetchApi(path, { body, method }) {
    const headers = new Headers({'Content-Type':'application/x-www-form-urlencoded'});

    const request = await fetch(`${apiUrl}${path}`, {headers: headers, method: method, body: body });
    const requestData = await request.json();
    if (requestData.status === 'error') {
        throw requestData.message;
    }
    return requestData;
}

export async function getActorsInfo() {
    const body = JSON.stringify({ 'method' : methods.listActors});
    return await fetchApi('/', {
        method: requestMethods.post,
        body
    });
}

export async function createNewActor(name, actorType) {
    const body = JSON.stringify({"method": methods.createActor, "nameActor" : name, "actorType" : actorType})

    return await fetchApi('/', {
        method: requestMethods.post,
        body
    });
}

export async function deleteActor(name) {
    const body = JSON.stringify({"method": methods.deleteActor, "nameActor" : name})

    return await fetchApi('/', {
        method: requestMethods.post,
        body
    });
}

export async function sendMessageActor(name, message) {
    const body = JSON.stringify({"method": methods.sendMessage, "nameActor" : name, "message": message})

    return await fetchApi('/', {
        method: requestMethods.post,
        body
    });
}