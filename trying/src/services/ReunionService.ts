import type IReunion from "@/interfaces/IReunion";

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

    async createReunion(tipo_reunion_id: number, fecha: Date){
        const data:IReunion = {
            id: 0,
            tipo_reunion_id: tipo_reunion_id,
            fecha: fecha
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteReunion(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateReunion(id: number, tipo_reunion_id: number, fecha: Date){
        const data:IReunion = {
            id: id,
            tipo_reunion_id: tipo_reunion_id,
            fecha: fecha
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