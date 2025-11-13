
# GRASP Example Project

Projeto Maven em Java demostrando a aplicação de princípios GRASP.

Pacote principal: `com.example.grasp`

Padrões GRASP aplicados:
- Controller
- Creator (livraria cria instâncias de Loan)
- Observações também sobre Low Coupling e High Cohesion (comentadas nas classes)

O exemplo implementa um sistema simplificado de biblioteca com:
- livro
- usuario
- livraria (contém coleções e cria Loans)
- emprestimo
- Controller (ponto de entrada / controlador que delega operações para o domínio)
