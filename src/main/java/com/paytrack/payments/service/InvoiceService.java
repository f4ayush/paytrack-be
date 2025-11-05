package com.paytrack.payments.service;

import com.paytrack.payments.dto.InvoiceDTO;
import com.paytrack.payments.dto.UpdateInvoiceDTO;
import com.paytrack.payments.entity.Client;
import com.paytrack.payments.entity.Invoice;
import com.paytrack.payments.entity.InvoiceItem;
import com.paytrack.payments.entity.User;
import com.paytrack.payments.repository.ClientRepository;
import com.paytrack.payments.repository.InvoiceItemRepository;
import com.paytrack.payments.repository.InvoiceRepository;
import com.paytrack.payments.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public Invoice create(InvoiceDTO invoiceData){
        User user = userRepository.findById(invoiceData.getUserId()).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        Client client = clientRepository.findById(invoiceData.getClientId()).orElseThrow(() -> new EntityNotFoundException("Client not found"));

        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice.setUser(user);
        invoice.setTotal(invoiceData.getTotal());
        invoice.setDueDate(invoiceData.getDueDate());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        System.out.println("Saved invoice ID: " + savedInvoice.getId());
        List<InvoiceItem> invoiceItems = invoiceData.getInvoiceItems().stream().map(dto ->{
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setDescription(dto.getDescription());
            invoiceItem.setQuantity(dto.getQuantity());
            invoiceItem.setPrice(dto.getPrice());
            invoiceItem.setTax(dto.getTax());
            invoiceItem.setInvoice(savedInvoice);
            return invoiceItem;
        }).collect(Collectors.toList());

        invoiceItemRepository.saveAll(invoiceItems);

        return invoice;
    }

    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll().stream().map(
                invoice -> {
                    List<InvoiceItem> invoiceItems = invoiceItemRepository.findAllByInvoiceId(invoice.getId());
                    return getTransformedInvoiceData(invoice, invoiceItems);
                }
        ).toList();
    }

    public InvoiceDTO get(String id) {
        System.out.println(id);
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Invoice not found"));
        System.out.println("aasasas" + invoice);
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAllByInvoiceId(invoice.getId());
        return getTransformedInvoiceData(invoice, invoiceItems);
    }

    private static InvoiceDTO getTransformedInvoiceData(Invoice invoice, List<InvoiceItem> invoiceItems) {
        return InvoiceDTO.builder().
                id(invoice.getId()).
                clientId(invoice.getClient().getId()).
                userId(invoice.getUser().getId()).
                total(invoice.getTotal())
                .status(invoice.getStatus().name())
                .dueDate(invoice.getDueDate())
                .createdAt(invoice.getCreatedAt()).
                invoiceItems(
                        invoiceItems.stream().map(
                                invoiceItem -> {
                                    return InvoiceDTO.InvoiceItems.
                                            builder()
                                            .description(invoiceItem.getDescription())
                                            .quantity(invoiceItem.getQuantity())
                                            .price(invoiceItem.getPrice())
                                            .tax(invoiceItem.getTax())
                                            .build();
                                }

                        ).toList()
                ).build();
    }

    public Invoice updateStatus(String id, UpdateInvoiceDTO invoiceData) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("invoice not found"));
        invoice.setStatus(invoiceData.getStatus());
        invoiceRepository.save(invoice);
        return invoice;
    }
}
