import type IReunion from "@/interfaces/IReunion";
import type ITipoReunion from "@/interfaces/ITipoReunion";

export default class ReunionService{

    baseUri: string = "http://127.0.0.1:8080/api/reuniones"

    async listAllReuniones(): Promise<Array<IReunion>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listReunionById(id: number): Promise<IReunion> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createReunion(tipoReunion: ITipoReunion, fecha: Date){
        const data:IReunion = {
            id: 0,
            tipoReunion: tipoReunion,
            fecha: fecha
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteReunion(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateReunion(id: number, tipoReunion: ITipoReunion, fecha: Date){
        const data:IReunion = {
            id: id,
            tipoReunion: tipoReunion,
            fecha: fecha
        }
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }
    
}