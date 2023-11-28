import org.springframework.jdbc.core.JdbcTemplate

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar() {
        jdbcTemplate = Conexao().conectar()
    }

    fun insertListagem(totalProcessos: Int) {
        jdbcTemplate.update("""
            insert into Listagem_Processos (dataHora, total_processos) values
            (now(), $totalProcessos);
        """)
    }

    fun insertProcesso(novoProcesso: String, novoPID: Int, novoCPU: String, novoRAM: String, novaFK: Int) {
        jdbcTemplate.update("""
            insert into Processos (nome_processo, PID, usoCPU, usoRAM, fkListagem) values
            ("$novoProcesso", $novoPID, $novoCPU, $novoRAM, $novaFK);
        """)
    }

}