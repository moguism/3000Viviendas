import type ICommunity from "@/interfaces/ICommunity";
import type IDeuda from "@/interfaces/IDeuda";
import type ITipoDeuda from "@/interfaces/ITipoDeuda";

export default class DeudaService {

    baseUri: string = "http://127.0.0.1:8080/api/deudas"

    async listAllDeudas(): Promise<Array<IDeuda>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listDeudaById(id: number): Promise<IDeuda> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createDeuda(tipoDeuda: ITipoDeuda, comunidad: ICommunity, fecha: Date, monto: number){
        const data:IDeuda = {
            id: 0,
            tipoDeuda: tipoDeuda,
            comunidad: comunidad,
            fecha : fecha,
            monto: monto
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

    async deleteDeuda(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateDeuda(id: number, tipoDeuda: ITipoDeuda, comunidad: ICommunity,  fecha: Date, monto: number){
        const uri = `${this.baseUri}/${id}`
        const data:IDeuda = {
            id: 0,
            tipoDeuda: tipoDeuda,
            comunidad: comunidad,
            fecha : fecha,
            monto: monto
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