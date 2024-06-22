package com.pfe.Bank.service;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.pfe.Bank.exception.MissingEntity;
import com.pfe.Bank.model.ClientProfes;
import com.pfe.Bank.model.SituationClientProfes;
import com.pfe.Bank.repository.ClientProfesRepository;
import com.pfe.Bank.repository.FinanceRepository;
import com.pfe.Bank.repository.SituationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SituationClientService {
    @Autowired
    private SituationRepository repository;

    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private ClientProfesRepository clientProfesRepository;

    public List<SituationClientProfes> getAllSituation() {

        return repository.findAll();
    }


    public List<SituationClientProfes> findByClientId(long clientId) {

        return repository.findByClientId(clientId);
    }


    public SituationClientProfes findById(long id) throws MissingEntity {
        Optional<SituationClientProfes> situation = repository.findById(id);
        if (!situation.isPresent()) {
            throw new MissingEntity("Client not found with id = : " + id);
        }
        return situation.get();
    }

    private static final Logger log = LoggerFactory.getLogger(SituationClientService.class);
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ClientProfes getClientById(Long clientId) throws MissingEntity {
        return clientProfesRepository.findById(clientId)
                .orElseThrow(() -> new MissingEntity("Client not found with id: " + clientId));
    }

    @Transactional
    public Set<SituationClientProfes> uploadSituations(MultipartFile file) throws IOException {
        Set<SituationClientProfes> situations = new HashSet<>();
        Set<SituationClientProfes> updatedSituations = parseCsv(file);

        for (SituationClientProfes csvLine : updatedSituations) {
            Optional<SituationClientProfes> existingSituationOptional = repository.findByClientAndDateDeSituation(csvLine.getClient(), csvLine.getDateDeSituation());
            if (existingSituationOptional.isPresent()) {
                SituationClientProfes existingSituation = existingSituationOptional.get();
                existingSituation.setNumeroComptePrincipal(csvLine.getNumeroComptePrincipal());
                existingSituation.setConsolidationInterne(csvLine.getConsolidationInterne());
                existingSituation.setMntEnConsolidation(csvLine.getMntEnConsolidation());
                existingSituation.setEncoursCT(csvLine.getEncoursCT());
                existingSituation.setEncoursMT(csvLine.getEncoursMT());
                existingSituation.setEncoursCreditTresorerie(csvLine.getEncoursCreditTresorerie());
                existingSituation.setRatioEngagementCDR(csvLine.getRatioEngagementCDR());
                existingSituation.setConsolidationAutresBanques(csvLine.getConsolidationAutresBanques());
                existingSituation.setBesoinAccompagnement(csvLine.getBesoinAccompagnement());
                existingSituation.setBesoinFinancement(csvLine.getBesoinFinancement());
                existingSituation.setTrancheEffectif(csvLine.getTrancheEffectif());
                existingSituation.setNombreClients(csvLine.getNombreClients());
                existingSituation.setEtatDuResultat(csvLine.getEtatDuResultat());
                existingSituation.setTailleBesoinFutur(csvLine.getTailleBesoinFutur());
                existingSituation.setClasseBanqueCentrale(csvLine.getClasseBanqueCentrale());
                existingSituation.setAnneeClassificationCentrale(csvLine.getAnneeClassificationCentrale());
                existingSituation.setRatingActuelleLegacy(csvLine.getRatingActuelleLegacy());
                existingSituation.setClasseRisqueLegacy(csvLine.getClasseRisqueLegacy());
                existingSituation.setScoreClientLegacy(csvLine.getScoreClientLegacy());
                existingSituation.setDateRatingLegacy(csvLine.getDateRatingLegacy());
                existingSituation.setImpaye(csvLine.getImpaye());
                existingSituation.setMontantImpayes(csvLine.getMontantImpayes());
                existingSituation.setRatioImpayesEngagements(csvLine.getRatioImpayesEngagements());
                existingSituation.setAncienneteImpayes(csvLine.getAncienneteImpayes());
                existingSituation.setMouvementsTotauxAnneeN(csvLine.getMouvementsTotauxAnneeN());
                existingSituation.setMouvementsTotauxAnneeN1(csvLine.getMouvementsTotauxAnneeN1());
                existingSituation.setMouvementCreditieurAnneeN(csvLine.getMouvementCreditieurAnneeN());
                existingSituation.setMouvementCreditieurAnneeN1(csvLine.getMouvementCreditieurAnneeN1());
                existingSituation.setMouvementDebiteurAnneeN(csvLine.getMouvementDebiteurAnneeN());
                existingSituation.setMouvementDebiteurAnneeN1(csvLine.getMouvementDebiteurAnneeN1());
                existingSituation.setRatioCreditSoldeMoyen(csvLine.getRatioCreditSoldeMoyen());
                existingSituation.setRegulariteEcheances(csvLine.getRegulariteEcheances());
                existingSituation.setDernierSalaireYTD(csvLine.getDernierSalaireYTD());
                existingSituation.setSoldeMoyenAnnuelAnneeN(csvLine.getSoldeMoyenAnnuelAnneeN());
                existingSituation.setSoldeMoyenAnnuelAnneeN1(csvLine.getSoldeMoyenAnnuelAnneeN1());
                existingSituation.setTotalCreancesGLE(csvLine.getTotalCreancesGLE());
                existingSituation.setVariationAnnuelleMvtCreditAnneeN(csvLine.getVariationAnnuelleMvtCreditAnneeN());
                existingSituation.setVariationAnnuelleMvtCreditAnneeN1(csvLine.getVariationAnnuelleMvtCreditAnneeN1());
                existingSituation.setVariationMvtCredit(csvLine.getVariationMvtCredit());
                existingSituation.setRationSoldeMoyenFC(csvLine.getRationSoldeMoyenFC());
                existingSituation.setIarCentralDesRisquesCDR(csvLine.getIarCentralDesRisquesCDR());
                existingSituation.setVariationEngagementCDR(csvLine.getVariationEngagementCDR());
                existingSituation.setVariationCreditTresoCDR(csvLine.getVariationCreditTresoCDR());
                existingSituation.setIncident(csvLine.getIncident());
                existingSituation.setModeleApplicable(csvLine.getModeleApplicable());
                existingSituation.setAutresInformation(csvLine.getAutresInformation());
                existingSituation.setCommentaire(csvLine.getCommentaire());
                existingSituation.setVariableLibre1(csvLine.getVariableLibre1());
                existingSituation.setVariableLibre2(csvLine.getVariableLibre2());
                existingSituation.setVariableLibre3(csvLine.getVariableLibre3());
                existingSituation.setVariableLibre4(csvLine.getVariableLibre4());
                existingSituation.setVariableLibre5(csvLine.getVariableLibre5());
                existingSituation.setVariableLibre6(csvLine.getVariableLibre6());
                existingSituation.setVariableLibre7(csvLine.getVariableLibre7());
                repository.save(existingSituation);
                situations.add(existingSituation);
                log.info("SituationClientProfes mise à jour : " + existingSituation);
            } else {
                repository.save(csvLine);
                situations.add(csvLine);
                log.info("Nouveau ClientRetail inséré : " + csvLine);
            }
        }
        return situations;
    }
    @Transactional
    private Set<SituationClientProfes> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            HeaderColumnNameMappingStrategy<SituationCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(SituationCsvRepresentation.class);
            CsvToBean<SituationCsvRepresentation> csvToBean = new CsvToBeanBuilder<SituationCsvRepresentation>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        try {
                            Date dateDeSituation = simpleDateFormat.parse(csvLine.getDateDeSituation());
                            Date dateRatingLegacy = simpleDateFormat.parse(csvLine.getDateRatingLegacy());
                            System.out.println(csvLine.toString());
                            Optional<ClientProfes> existingClientOptional = clientProfesRepository.findByCodeRelation(csvLine.getCodeRelation());
                            if (!existingClientOptional.isPresent()) {
                                throw new MissingEntity("Client not found with code relation: " + csvLine.getCodeRelation());

                            }
                            ClientProfes existingClient = existingClientOptional.get();

                            SituationClientProfes situationClientProfes = SituationClientProfes.builder()
                                    .clientProfes(existingClient)
                                    .codeRelation(csvLine.getCodeRelation())
                                    .dateDeSituation(simpleDateFormat.parse(csvLine.getDateDeSituation()))
                                    .numeroComptePrincipal(csvLine.getNumeroComptePrincipal())
                                    .consolidationInterne(csvLine.getConsolidationInterne())
                                    .mntEnConsolidation(csvLine.getMntEnConsolidation())
                                    .encoursCT(csvLine.getEncoursCT())
                                    .encoursMT(csvLine.getEncoursMT())
                                    .encoursCreditTresorerie(csvLine.getEncoursCreditTresorerie())
                                    .ratioEngagementCDR(csvLine.getVariationEngagementCDR())
                                    .consolidationAutresBanques(csvLine.getConsolidationAutresBanques())
                                    .besoinAccompagnement(csvLine.getBesoinAccompagnement())
                                    .besoinFinancement(csvLine.getBesoinFinancement())
                                    .trancheEffectif(csvLine.getTrancheEffectif())
                                    .nombreClients(csvLine.getNombreClients())
                                    .etatDuResultat(csvLine.getEtatDuResultat())
                                    .tailleBesoinFutur(csvLine.getTailleBesoinFutur())
                                    .classeBanqueCentrale(csvLine.getClasseBanqueCentrale())
                                    .anneeClassificationCentrale(csvLine.getAnneeClassificationCentrale())
                                    .ratingActuelleLegacy(csvLine.getRatingActuelleLegacy())
                                    .classeRisqueLegacy(csvLine.getClasseRisqueLegacy())
                                    .scoreClientLegacy(csvLine.getScoreClientLegacy())
                                    .dateRatingLegacy(simpleDateFormat.parse(csvLine.getDateRatingLegacy()))
                                    .impaye(csvLine.getImpaye())
                                    .montantImpayes(csvLine.getMontantImpayes())
                                    .ratioImpayesEngagements(csvLine.getRatioImpayesEngagements())
                                    .ancienneteImpayes(csvLine.getAncienneteImpayes())
                                    .mouvementsTotauxAnneeN(csvLine.getMouvementsTotauxAnneeN())
                                    .mouvementCreditieurAnneeN1(csvLine.getMouvementsTotauxAnneeN1())
                                    .mouvementCreditieurAnneeN(csvLine.getMouvementCreditieurAnneeN())
                                    .mouvementCreditieurAnneeN1(csvLine.getMouvementCreditieurAnneeN1())
                                    .mouvementDebiteurAnneeN(csvLine.getMouvementDebiteurAnneeN())
                                    .mouvementDebiteurAnneeN1(csvLine.getMouvementDebiteurAnneeN1())
                                    .ratioCreditSoldeMoyen(csvLine.getRatioCreditSoldeMoyen())
                                    .regulariteEcheances(csvLine.getRegulariteEcheances())
                                    .dernierSalaireYTD(csvLine.getDernierSalaireYTD())
                                    .soldeMoyenAnnuelAnneeN(csvLine.getSoldeMoyenAnnuelAnneeN())
                                    .soldeMoyenAnnuelAnneeN1(csvLine.getSoldeMoyenAnnuelAnneeN1())
                                    .totalCreancesGLE(csvLine.getTotalCreancesGLE())
                                    .variationAnnuelleMvtCreditAnneeN(csvLine.getVariationAnnuelleMvtCreditAnneeN())
                                    .variationAnnuelleMvtCreditAnneeN1(csvLine.getVariationAnnuelleMvtCreditAnneeN1())
                                    .variationMvtCredit(csvLine.getVariationMvtCredit())
                                    .rationSoldeMoyenFC(csvLine.getRationSoldeMoyenFC())
                                    .iarCentralDesRisquesCDR(csvLine.getIarCentralDesRisquesCDR())
                                    .variationEngagementCDR(csvLine.getVariationEngagementCDR())
                                    .mntCreditTresorerieCDR(csvLine.getMntCreditTresorerieCDR())
                                    .variationCreditTresoCDR(csvLine.getVariationCreditTresoCDR())
                                    .incident(csvLine.getIncident())
                                    .modeleApplicable(csvLine.getModeleApplicable())
                                    .autresInformation(csvLine.getAutresInformation())
                                    .commentaire(csvLine.getCommentaire())
                                    .variableLibre1(csvLine.getVariableLibre1())
                                    .variableLibre2(csvLine.getVariableLibre2())
                                    .variableLibre3(csvLine.getVariableLibre3())
                                    .variableLibre4(csvLine.getVariableLibre4())
                                    .variableLibre5(csvLine.getVariableLibre5())
                                    .variableLibre6(csvLine.getVariableLibre6())
                                    .variableLibre7(csvLine.getVariableLibre7())
                                    .build();

                            System.out.println(situationClientProfes.getNumeroComptePrincipal());

                            return situationClientProfes;
                        } catch (ParseException e) {
                            log.error("Parsing error for date: {}", csvLine.getDateDeSituation(), e);
                            throw new RuntimeException(e);
                        } catch (MissingEntity e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet());
        }
    }
    }

