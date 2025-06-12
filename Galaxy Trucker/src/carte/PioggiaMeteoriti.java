package carte;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import componenti.Componente;
import componenti.Connettore;
import componenti.Lato;
import galaxyTrucker.Gioco;
import galaxyTrucker.Nave;
import galaxyTrucker.Posizione;

public class PioggiaMeteoriti extends Carta{
	private final List<Meteorite> meteoriti;

	public PioggiaMeteoriti(Integer id, String nome, Livello livello, int ggVolo, int merce,
			int equipaggio, int credito, List<Meteorite> meteoriti) {
		super(id, NomeSpeciale.PIOGGIA_METEORITI , nome, livello, ggVolo, merce, equipaggio, credito);
		this.meteoriti=meteoriti;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Che prevede (in ordine di arrivo) " + meteoriti;
	}
	
	void attivaCarta(Gioco flotta) {
	    List<Nave> ordinate = flotta.getFlottaNaveOrdinata();
	    Scanner scanner = new Scanner(System.in);
	    Random rand = new Random();

	    for (Meteorite m : meteoriti) {
	        int tiro = rand.nextInt(6) + 1 + rand.nextInt(6) + 1 - 3; // da -1 a 9
	        System.out.println("\nLancio dei dadi per " + m + ", risultato: " + tiro);

	        for (Nave nave : ordinate) {
	            int scelta;
	            while (true) {
	                System.out.print("Giocatore " + nave.getColor() + ", vuoi difenderti con uno scudo? (0=No, 1=Si): ");
	                try {
	                    scelta = scanner.nextInt();
	                    scanner.nextLine();
	                    if (scelta == 0 || scelta == 1) break;
	                } catch (InputMismatchException e) {
	                    scanner.nextLine();
	                }
	                System.out.println("Input non valido! Inserisci 0 o 1.");
	            }

	            boolean difeso = false;
	            if (scelta == 1) {
	                if (m.getDimensione() == Dimensione.piccolo && nave.isLatoProtetto(m.getLato())) {
	                    if (nave.getEnergiaNave() >= 1) {
	                        nave.minusEnergiaNave(1);
	                        System.out.println("Difesa riuscita con lo scudo!");
	                        difeso = true;
	                    } else {
	                        System.out.println("Energia insufficiente per lo scudo.");
	                    }
	                } else {
	                    System.out.println("Non puoi usare lo scudo su questo meteorite.");
	                }
	            }

	            if (!difeso && tiro >= 2 && tiro <= 12 && nave.isLatoProtetto(m.getLato(), tiro)) {
	                int sceltaC;
	                while (true) {
	                    System.out.print("Cannone a portata. Usarlo? (0=No, 1=Si): ");
	                    try {
	                        sceltaC = scanner.nextInt();
	                        scanner.nextLine();
	                        if (sceltaC == 0 || sceltaC == 1) break;
	                    } catch (InputMismatchException ex) {
	                        scanner.nextLine();
	                    }
	                    System.out.println("Input non valido! Inserisci 0 o 1.");
	                }
	                if (sceltaC == 1) {
	                    if (nave.getEnergiaNave() >= 1) {
	                        nave.minusEnergiaNave(1);
	                        System.out.println("Difesa riuscita con il cannone!");
	                        difeso = true;
	                    } else {
	                        System.out.println("Energia insufficiente per il cannone.");
	                    }
	                }
	            }

	            boolean fermato = false;
	            int nR = nave.getNRighe(), nC = nave.getNColonne();
	            int idx = tiro; 

	            if (m.getLato() == Lato.up && idx >= 0 && idx < nC) {
	                for (int r = 0; r < nR; r++) {
	                    Componente c = nave.getCella(new Posizione(r, idx)).getComponente();
	                    if (c != null) {
	                        fermato = true;
	                        if (m.getDimensione() == Dimensione.piccolo) {
	                            if (c.getConnettori(m.getLato().opposto()) == Connettore.assente)
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(r, idx);
	                               nave.aggiornaStatoConnessioni();
	                               nave.eliminaDisconnessi();
	                                System.out.println("Meteorite piccolo distrugge componente in (" + r + "," + idx + ").");
	                            }
	                        } else {
	                            nave.eliminaComponente(r, idx);
	                            nave.aggiornaStatoConnessioni();
	                               nave.eliminaDisconnessi();
	                            System.out.println("Meteorite grande distrugge componente in (" + r + "," + idx + ").");
	                        }
	                        break;
	                    }
	                }
	            } else if (m.getLato() == Lato.dw && idx >= 0 && idx < nC) {
	                for (int r = nR - 1; r >= 0; r--) {
	                    Componente c = nave.getCella(new Posizione(r, idx)).getComponente();
	                    if (c != null) {
	                        fermato = true;
	                        if (m.getDimensione() == Dimensione.piccolo) {
	                            if (c.getConnettori(m.getLato().opposto()) == Connettore.assente)
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(r, idx);
	                                nave.aggiornaStatoConnessioni();
		                               nave.eliminaDisconnessi();
	                                System.out.println("Meteorite piccolo distrugge componente in (" + r + "," + idx + ").");
	                            }
	                        } else {
	                            nave.eliminaComponente(r, idx);
	                            nave.aggiornaStatoConnessioni();
	                               nave.eliminaDisconnessi();
	                            System.out.println("Meteorite grande distrugge componente in (" + r + "," + idx + ").");
	                        }
	                        break;
	                    }
	                }
	            } else if (m.getLato() == Lato.sx && idx >= 0 && idx < nR) {
	                for (int c = 0; c < nC; c++) {
	                    Componente comp = nave.getCella(new Posizione(idx, c)).getComponente();
	                    if (comp != null) {
	                        fermato = true;
	                        if (m.getDimensione() == Dimensione.piccolo) {
	                            if (comp.getConnettori(m.getLato().opposto()) == Connettore.assente)
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(idx, c);
	                                nave.aggiornaStatoConnessioni();
		                               nave.eliminaDisconnessi();
	                                System.out.println("Meteorite piccolo distrugge componente in (" + idx + "," + c + ").");
	                            }
	                        } else {
	                            nave.eliminaComponente(idx, c);
	                            nave.aggiornaStatoConnessioni();
	                               nave.eliminaDisconnessi();
	                            System.out.println("Meteorite grande distrugge componente in (" + idx + "," + c + ").");
	                        }
	                        break;
	                    }
	                }
	            } else if (m.getLato() == Lato.dx && idx >= 0 && idx < nR) {
	                for (int c = nC - 1; c >= 0; c--) {
	                    Componente comp = nave.getCella(new Posizione(idx, c)).getComponente();
	                    if (comp != null) {
	                        fermato = true;
	                        if (m.getDimensione() == Dimensione.piccolo) {
	                            if (comp.getConnettori(m.getLato().opposto()) == Connettore.assente)
	                                System.out.println("Meteorite piccolo rimbalza sul lato liscio.");
	                            else {
	                                nave.eliminaComponente(idx, c);
	                                nave.aggiornaStatoConnessioni();
		                               nave.eliminaDisconnessi();
	                                System.out.println("Meteorite piccolo distrugge componente in (" + idx + "," + c + ").");
	                            }
	                        } else {
	                            nave.eliminaComponente(idx, c);
	                            nave.aggiornaStatoConnessioni();
	                               nave.eliminaDisconnessi();
	                            System.out.println("Meteorite grande distrugge componente in (" + idx + "," + c + ").");
	                        }
	                        break;
	                    }
	                }
	            }

	            if (!fermato) {
	                System.out.println("Meteorite attraversa senza danni la nave di " + nave.getColor());
	            }
	        }
	    }
	}
}