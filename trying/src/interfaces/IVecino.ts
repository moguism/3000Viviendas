import type IVivienda from "./IVivienda";

export default interface IVecino {
    id: number,
    nombre: string,
    viviendas: Array<IVivienda>
}
