import type IContratante from "@/interfaces/IContratante";

export default class ContratanteService {

    baseUri: string = "http://127.0.0.1:8080/api/contratantes"

    async listAllContratantes(): Promise<Array<IContratante>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listContratanteById(id: number): Promise<IContratante> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createContratante(nombre: string){
        const data:IContratante = {
            id: 0,
            nombre: nombre,
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

    async deleteContratante(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateContratante(id: number, nombre: string){
        const uri = `${this.baseUri}/${id}`
        const data:IContratante = {
            id: 0,
            nombre: nombre,
        }
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