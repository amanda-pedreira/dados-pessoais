package br.senac.tads.dsw.dados_pessoais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;


@Service
public class PessoaService {
    private AtomicInteger contador = new AtomicInteger(0);
    private Map<String, PessoaDto> mapPessoas = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        mapPessoas.put("amanda", new PessoaDto (contador.incrementAndGet(),
        "amanda", "Amanda Luana Pedreira",
        "Amandaluanap7@email.com", "(11) 91234-5678", LocalDate.parse("2006-07-07")));
        mapPessoas.put("luana", new PessoaDto (contador.incrementAndGet(),
        "luana", "Luana Pedreira",
        "luanapedreira@email.com", "(11) 91111-2222", LocalDate.parse("2000-01-01")));
        mapPessoas.put("emilly", new PessoaDto (contador.incrementAndGet(),
        "emilly", "Emilly Marqueti",
        "emilly@email.com", "(11) 93333-4444", LocalDate.parse("2010-10-10")));
        mapPessoas.put("isabele", new PessoaDto (contador.incrementAndGet(),
        "isabele", "Isabele Laranjo",
        "isabele@email.com", "(11) 95555-6666", LocalDate.parse("2005-05-05")));
    }

    public List<PessoaDto> obterPessoas() {
        return new ArrayList<>(mapPessoas.values());
    }

    public Optional<PessoaDto> obterPessoa (String username) {
        return Optional.ofNullable(mapPessoas.get(username));
    }

    public PessoaDto incluirNovaPessoa(PessoaDto pessoa){
        pessoa.setId(contador.incrementAndGet());
        mapPessoas.put(pessoa.getUsername(), pessoa);
        return pessoa;
    }

    public PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao){
        if(!mapPessoas.containsKey(username)) {
            throw new NaoEncontradoException("Pessoa " + username + " não encontrada");
        }

        PessoaDto pessoaOriginal = mapPessoas.get(username);
        pessoaOriginal.setNome(pessoaAlteracao.getNome());
        pessoaOriginal.setEmail(pessoaAlteracao.getEmail());
        pessoaOriginal.setTelefone(pessoaAlteracao.getTelefone());
        pessoaOriginal.setDataNascimento(pessoaAlteracao.getDataNascimento());
        pessoaOriginal.setConhecimentos(pessoaAlteracao.getConhecimentos());

        return pessoaOriginal;
    }

    public void removerPessoa(String username){
        if (!mapPessoas.containsKey(username)){
            throw new NaoEncontradoException("Pessoa " + username + " não encontrada");
        }
        mapPessoas.remove(username);
    }
}