import type IBloque from "./IBloque";
import type IVivienda from "./IVivienda";

export default interface IMensualidad {
    id: number,
    fecha: Date,
    cuantia: number,
    bloque: IBloque,
    viviendas: Array<IVivienda>
}