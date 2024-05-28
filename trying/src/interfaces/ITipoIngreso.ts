import type IIngreso from "./IIngreso";

export default interface ITipoIngreso {
    id: number,
    nombre: string,
    ingresos: Array<IIngreso>
}