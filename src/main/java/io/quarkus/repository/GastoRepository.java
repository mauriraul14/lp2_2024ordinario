package io.quarkus.repository;
import io.quarkus.model.Gasto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GastoRepository {
    private static final String DATA_FILE = "src/main/resources/data.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    // Leer los gastos desde el archivo JSON
    public List<Gasto> getAllGastos() throws IOException {
        return objectMapper.readValue(new File(DATA_FILE),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Gasto.class));
    }

    // Crear un nuevo gasto
    public void addGasto(Gasto gasto) throws IOException {
        List<Gasto> gastos = getAllGastos();
        gastos.add(gasto);
        objectMapper.writeValue(new File(DATA_FILE), gastos);
    }

    // Obtener un gasto por ID
    public Gasto getGastoById(int id) throws IOException {
        List<Gasto> gastos = getAllGastos();
        return gastos.stream().filter(gasto -> gasto.getId() == id).findFirst().orElse(null);
    }

    // Actualizar un gasto
    public void updateGasto(int id, Gasto updatedGasto) throws IOException {
        List<Gasto> gastos = getAllGastos();
        gastos = gastos.stream().map(gasto -> gasto.getId() == id ? updatedGasto : gasto).collect(Collectors.toList());
        objectMapper.writeValue(new File(DATA_FILE), gastos);
    }

    // Eliminar un gasto
    public void deleteGasto(int id) throws IOException {
        List<Gasto> gastos = getAllGastos();
        gastos = gastos.stream().filter(gasto -> gasto.getId() != id).collect(Collectors.toList());
        objectMapper.writeValue(new File(DATA_FILE), gastos);
    }

    // Calcular el promedio de los gastos
    public double getPromedioGastos() throws IOException {
        List<Gasto> gastos = getAllGastos();
        return gastos.stream().mapToDouble(Gasto::getMonto).average().orElse(0.0);
    }

    // Filtrar gastos por rango de fechas
    public List<Gasto> filterGastosByDateRange(long inicio, long fin) throws IOException {
        List<Gasto> gastos = getAllGastos();
        return gastos.stream().filter(gasto -> gasto.getFecha() >= inicio && gasto.getFecha() <= fin).collect(Collectors.toList());
    }
}
