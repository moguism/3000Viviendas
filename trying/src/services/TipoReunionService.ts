import type ITipoReunion from "@/interfaces/ITipoReunion";

export default class TipoReunionService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-reunion"

    async listAllTiposReunion(): Promise<Array<ITipoReunion>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoReunionById(id: number): Promise<ITipoReunion> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoReunion(name: string) {
        const data:ITipoReunion = {
            id: 0,
            nombre: name
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteTipoReunion(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateTipoReunion(id: number, name: string) {
        const data:ITipoReunion = {
            id: id,
            nombre: name
        }
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}