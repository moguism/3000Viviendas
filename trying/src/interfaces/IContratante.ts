import type IContrato from "./IContrato";

export default interface IContratante {
    id: number,
    nombre: string,
    contratos: Array<IContrato>
}