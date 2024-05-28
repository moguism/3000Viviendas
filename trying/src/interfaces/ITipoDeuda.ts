import type IDeuda from "./IDeuda";

export default interface ITipoDeuda {
    id: number,
    nombre: string,
    deudas: Array<IDeuda>
}