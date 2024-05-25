import type ICommunity from "@/interfaces/ICommunity.ts"
import type IBloque from "@/interfaces/IBloque.ts"
import type IContrato from "@/interfaces/IContrato.js";
import type IDeuda from "@/interfaces//IDeuda.js";
import type IGasto from "@/interfaces/IGasto.js";
import type IIngreso from "@/interfaces/IIngreso.js";
import type ILocal from "@/interfaces/ILocal.js";

export default class CommunityService {

    baseUri:string = "http://127.0.0.1:8080/api/comunidades"

    async listAllCommunities():Promise<Array<ICommunity>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listCommunityById(id:number):Promise<ICommunity> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createCommunity(name:string, address:string, bloques:Array<IBloque>, contratos:Array<IContrato>, locales:Array<ILocal>, deudas:Array<IDeuda>, ingresos:Array<IIngreso>, gastos:Array<IGasto>) {
        const data:ICommunity = {
            id: 0,
            nombre: name,
            direccion: address,
            bloques: bloques,
            contratos: contratos,
            locales: locales,
            deudas: deudas,
            ingresos: ingresos,
            gastos: gastos
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

    async deleteComunidad(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
        // No devuelve nada porque Spring no lo hace
    }

    async updateComunidad(id:number, name:string, address:string, bloques:Array<IBloque>, contratos:Array<IContrato>, locales:Array<ILocal>, deudas:Array<IDeuda>, ingresos:Array<IIngreso>, gastos:Array<IGasto>) {
        const uri = `${this.baseUri}/${id}`
        const data:ICommunity = {
            id : 0,
            nombre: name,
            direccion: address,
            bloques: bloques,
            contratos: contratos,
            locales: locales,
            deudas: deudas,
            ingresos: ingresos,
            gastos: gastos
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