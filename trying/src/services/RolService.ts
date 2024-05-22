import type IRol from "@/interfaces/IRol";

export default class RolService {

    baseUri: string = "http://127.0.0.1:8080/api/roles"

    async listAllRoles(): Promise<Array<IRol>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listRolById(id: number): Promise<IRol> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createRol(nombre: string){
        const data:IRol = {
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

    async deleteRol(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateRol(id: number, nombre: string){
        const uri = `${this.baseUri}/${id}`
        const data:IRol = {
            id: 0,
            nombre: nombre,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}